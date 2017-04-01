class ComplaintController < ApplicationController

  before_action :check_user_logged_in
  before_action :check_user_logged_in_as_admin, only: [:assign_complaint]

  def create
    complaint = Complaint.new(subject: params[:subject],
                                description: params[:description],
                                image: params[:image],
                                latitude: params[:latitude],
                                longitude: params[:longitude],
                                address: params[:address],
                                district: params[:district],
                                state: params[:state],
                                pincode: params[:pincode],
                                user_id: get_logged_in_user_id,
                                status: "new" ,
                                priority: "new")

    user = User.find(get_logged_in_user_id)

      if complaint.save
        # assign new complaint to respective district office
        if params[:ward]
          assignment_result = auto_assign_complaint(complaint.id,
                                                complaint.state,
                                                complaint.district,
                                                complaint.subject,
                                                params[:ward])
        else
          assignment_result = register_new_complaint(complaint.id,
                                                      complaint.state,
                                                      complaint.district,
                                                      complaint.subject)
        end
        #send_sms(user.contact, "Your complaint has been registered. Your complaint id is -" + complaint.id)
        render json: {status: "success", complaint: complaint, message: assignment_result}
      else
        render json: {status: "error", error_message: complaint.errors.full_messages}
      end
  end

  def show_user_complaints

    user_id = get_logged_in_user_id

    complaints = Complaint.where(user_id: user_id)

    render json: complaints

  end

  # get complaint by complaint id
  def show_complaint_by_id

    complaint = Complaint.find(params[:id])
    if complaint
      render json: complaint
    else
      render json: {status: "error", error_message: "complaint not found"}
    end

  end

  def create_alert



  end

private

  # assign new complaint to respective district office
  def register_new_complaint(complaint_id, state, district, subject_of_complaint)

    district_office = DistrictOffice.where(state: state, district: district).first

    if district_office

      complaint_update = ComplaintUpdate.new(complaint_id: complaint_id,
                                             assigned_to: "District Municipal Officer " + district,
                                             notes: "Auto Assignment by System to concerned district office")

      complaint_status = ComplaintStatus.new(complaint_id: complaint_id,
                                       district_office_id: district_office.id,
                                       department: subject_of_complaint)

      if complaint_update.save && new_complaint.save
        return "Complaint forwarded to concerned officer"
      else
        return "Update to complaint failed"
      end

    else
      return "Data for concerned Municipal office doesn't exist"
    end

  end

  #assign complaint to superviser if ward is present in complaint
  # else call district office assignment if district office is present
  # else return no data present for district

#  def auto_assign_complaint(complaint_id, state, district, subject, ward)
#
#    district_office = DistrictOffice.where(state: state,
#                                                district: district).first
#    if district_office
#      ward_office = WardOffice.where(district_office_id: district_office.id
#                                      ward: ward)
#      if ward_office
#          # finding superviser with least active complaints
#          all_ward_supervisers = AdminUser.where(designation: "superviser",
#                                                municipal_id: ward_office.id,
#                                                department: subject)
#          least_complaints = 9999
#
#          all_ward_supervisers.each do |superviser|
#            total_complaints = ComplaintStatus.where(admin_user_id: superviser.id,
#                                                      status: "active")
#            if total_complaints < least_complaints
#              least_complaints = total_complaints
#              least_complaints_user_id = superviser.id
#            end
#
#          final_superviser = AdminUser.find(least_complaints_user_id)
#
#          complaint_update = ComplaintUpdate.new(complaint_id: complaint_id,
#                                                 assigned_to: "superviser: " + final_superviser.name,
#                                                 notes: "Auto Assignment by System to concerned district office")
#
#          complaint_status = ComplaintStatus.new(complaint_id: complaint_id,
#                                                  district_office_id: district_office.id,
#                                                  ward_office_id: ward_office.id,
#                                                  department: subject,
#                                                  status: "new")
#
#      else
#        register_new_complaint(complaint_id, state, district, subject)
#      end
#
#    else
#      return "No data for district office complaint can't be forwarded at the moment"
#   end


end
