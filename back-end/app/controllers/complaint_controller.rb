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
        assignment_result = register_new_complaint(complaint.id, complaint.state, complaint.district, complaint.subject)
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

  def assign_complaint #assign complaint to ward officers or a contractor

    user = AdminUser.find(get_logged_in_user_id)

    complaint = Complaint.find(params[:complaint_id])

    complaint_status = ComplaintStatus.where(complaint_id: params[:id]).first
    if complaint_update

      if user.designation == "District Officer" && params[:complaint_id] && params[] && params[]
        complaint_update = ComplaintUpdate.new(complaint_id: params[:id],
                                                assigned_to: find_ward_officer(params[:complaint_id],
                                                                                user.municipal_id, ),
                                                notes: params[:notes])

        complaint_status.ward_office_id = params[:ward_office_id]
        complaint_status.notes = params[:notes]


    elsif user.designation == "Ward Officer"
      ward_officer=ComplaintStatus.where(ward_office_id: params[:id]).first

    else

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
                                             notes: "Auto Assignment by System")

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

end
