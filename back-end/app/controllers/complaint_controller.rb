class ComplaintController < ApplicationController

  before_action :check_user_logged_in
  before_action :check_user_logged_in_as_admin, only: [:assign_complaint]

  def create
    complaint = Complaint.new(subject: params[:subject],
                                sub_category: params[:sub_category],
                                description: params[:description],
                                image: params[:image],
                                latitude: params[:latitude],
                                longitude: params[:longitude],
                                address: params[:address],
                                district: params[:district],
                                state: params[:state],
                                pincode: params[:pincode],
                                user_id: get_logged_in_user_id)

    user = User.find(get_logged_in_user_id)

      if complaint.save
        # assign new complaint to respective district office
          assignment_result = register_new_complaint(complaint.id,
                                                      complaint.state,
                                                      complaint.district,
                                                      complaint.subject,
                                                      complaint.sub_category)
        #send_sms(user.contact, "Your complaint has been registered. Your complaint id is -" + complaint.id)
        render json: {status: "success", complaint: complaint, message: assignment_result}
      else
        render json: {status: "error", error_message: complaint.errors.full_messages}
      end
  end

  def show_user_complaints

    user_id = get_logged_in_user_id

    complaints = Complaint.where(user_id: user_id)

    complaints.each do |complaint|
      complaint_status = ComplaintStatus.where(complaint_id: complaint.id).first
      if complaint_status
        complaint.status = complaint_status.status
      else
        complaint.status = "New"
      end
    end

    render json: complaints, methods: [:status]

  end

  # get complaint by complaint id
  def show_complaint_by_id

    complaint = Complaint.find(params[:id])
    if complaint
      complaint_status = ComplaintStatus.where(complaint_id: complaint.id).first
      if complaint_status
        complaint.status = complaint_status.status
      else
        complaint.status = "New"
      end
      render json: complaint, methods: [:status]
    else
      render json: {status: "error", error_message: "complaint not found"}
    end

  end

  def create_alert

    if params[:complaint_id] && params[:message]

      complaint = Complaint.find(params[:complaint_id])
      previous_alert = Alert.where(complaint_id: params[:complaint_id])

      if previous_alert

          time_elapsed = (Time.now() - previous_alert.created_at)/3600
          time_gap_needed = Sla.where(category: complaint.subject,
                                      sub_category: complaint.sub_category)

          if time_gap_needed

              if time_gap_needed.time <= time_elapsed

                alerted_user = AdminUser.find(previous_alert.admin_user_id)

                if alerted_user.designation == "district officer"
                  render json: {status: "error", error_message: "We have already alerted the highest reachable Authority! Please contact regional Municipal head"}
                elsif alerted_user.designation == "ward officer"

                  district_office = DistrictOffice.where(state: complaint.state,
                                                          district: complaint.district).first
                  person_to_alert = AdminUser.where(designation: "district officer", municipal_id: district_office.id).first

                  new_alert = Alert.new(complaint_id: params[:complaint_id],
                                        admin_user_id: person_to_alert.id,
                                        message: params[:message])

                  complaint_update = ComplaintUpdate.new(complaint_id: complaint_id,
                                                         assigned_to:  person_to_alert.name,
                                                         notes: "Escalated to district officer")
                  if new_alert.save && complaint_update.save
                    render json: {status: "success", message: "alert created succesfully!"}
                  end
               elsif alerted_user.designation == "superviser"

                district_office = DistrictOffice.where(state: complaint.state,
                                                        district: complaint.district).first

                ward_office = WardOffice.where(district_office_id: district_office.id,
                                               ward: complaint.ward)

                person_to_alert = AdminUser.where(designation: "ward officer", municipal_id: ward_office.id).first

                new_alert = Alert.new(complaint_id: params[:complaint_id],
                                      admin_user_id: person_to_alert.id,
                                      message: params[:message])

                complaint_update = ComplaintUpdate.new(complaint_id: complaint_id,
                                                       assigned_to:  person_to_alert.name,
                                                       notes: "Escalated to ward officer")
              end
            else
              render json: {status: "error", error_message: "You need to wait longer before you can register and alert"}
            end
          else
            render json: {status: "error", error_message: "SLA Data not available for this field"}
          end
        else

          time_elapsed = (Time.now() - complaint.created_at)/3600
          time_gap_needed = Sla.where(category: complaint.subject,
                                      sub_category: complaint.sub_category)

          if time_gap_needed.time <= time_elapsed

              complaint_status = ComplaintStatus.where(complaint_id: complaint.id).first
              person_to_alert = AdminUser.find(complaint_status.admin_user_id)

              new_alert = Alert.new(complaint_id: params[:complaint_id],
                                    admin_user_id: person_to_alert.id,
                                    message: params[:message])

              complaint_update = ComplaintUpdate.new(complaint_id: complaint_id,
                                                     assigned_to:  person_to_alert.name,
                                                     notes: "Alert raised to concerned superviser")
              if new_alert.save && complaint_update.save
                render json: {status: "success", message: "alert created succesfully!"}
              end
            else
              render json: {status: "error", error_message: "you need to wait before you can raise an alert"}
            end
        end
      else
        render json: {status: "error", error_message: "Message or Complaint Id can't be blank"}
      end
  end

#  def assign_complaint_to_ward
#
#
#    district_office = DistrictOffice.where(state: complaint.state,
#                                                district: complaint.district).first
#    if district_office
#      ward_office = WardOffice.where(district_office_id: district_office.id,
#                                      ward: params[:ward])
#
#            complaint_update = ComplaintUpdate.new(complaint_id: complaint_id,
#                                                   assigned_to: "superviser: " + final_superviser.name,
#                                                   notes: "Auto Assignment by System to concerned district office")
#
#            complaint_status = ComplaintStatus.new(complaint_id: complaint_id,
#                                                    district_office_id: district_office.id,
#                                                    ward_office_id: ward_office.id,
#                                                    department: subject,
#                                                    sub_category: sub_category,
#                                                    status: "new")
#
#          if complaint_status.save && complaint_update.save
#            render json: {status: "success", }
#          end
#  end

private

  # assign new complaint to respective district office
  def register_new_complaint(complaint_id, state, district, subject_of_complaint, sub_category)

    district_office = DistrictOffice.where(state: state, district: district).first

    if district_office

      district_admin = AdminUser.where(designation: "district officer",
                                        municipal_id: district_office.id).first
      if district_admin

        complaint_update = ComplaintUpdate.new(complaint_id: complaint_id,
                                               assigned_to: "District Municipal Officer: " + district_admin.name,
                                               notes: "Auto Assignment by System to concerned district office")

        complaint_status = ComplaintStatus.new(complaint_id: complaint_id,
                                         admin_user_id: district_admin.id,
                                         district_office_id: district_office.id,
                                         department: subject_of_complaint,
                                         status: "new")

        if complaint_update.save && complaint_status.save
          return "Complaint forwarded to concerned officer"
        else
          return "Update to complaint failed"
        end
      else
        return "Data for concerned Municipal officer doesn't exist"
      end
    else
      return "Data for concerned Municipal office doesn't exist"
    end

  end

  #assign complaint to superviser if ward is present in complaint
  # else call district office assignment if district office is present
  # else return no data present for district

  def auto_assign_complaint(complaint_id, state, district, subject, ward, sub_category)

    district_office = DistrictOffice.where(state: state,
                                                district: district).first
    if district_office
      ward_office = WardOffice.where(district_office_id: district_office.id,
                                      ward: ward)
      if ward_office
          # finding superviser with least active complaints
          all_ward_supervisers = AdminUser.where(designation: "superviser",
                                                municipal_id: ward_office.id,
                                                department: subject)
          if all_ward_supervisers
            least_complaints = 9999

            all_ward_supervisers.each do |superviser|
              total_complaints = ComplaintStatus.where(admin_user_id: superviser.id,
                                                        status: "active")
              if total_complaints < least_complaints
                least_complaints = total_complaints
                least_complaints_user_id = superviser.id
              end
            end

            final_superviser = AdminUser.find(least_complaints_user_id)

            complaint_update = ComplaintUpdate.new(complaint_id: complaint_id,
                                                   assigned_to: "superviser: " + final_superviser.name,
                                                   notes: "Auto Assignment by System to concerned district office")

            complaint_status = ComplaintStatus.new(complaint_id: complaint_id,
                                                    district_office_id: district_office.id,
                                                    ward_office_id: ward_office.id,
                                                    department: subject,
                                                    sub_category: sub_category,
                                                    status: "new")

          if complaint_status.save && complaint_update.save
            return "Auto Assignment Complete!"
          else
            register_new_complaint(complaint_id, state, district, subject)
            return "Databse couldn't be updated"
          end
        else
          register_new_complaint(complaint_id, state, district, subject)
          return "No employees registered for the ward"
        end
      else
        register_new_complaint(complaint_id, state, district, subject)
    end

    else
      return "No data for district office complaint can't be forwarded at the moment"
   end
 end

end
