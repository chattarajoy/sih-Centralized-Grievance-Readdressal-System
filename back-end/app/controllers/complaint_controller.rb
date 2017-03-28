class ComplaintController < ApplicationController

  before_action :check_user_logged_in

  def create
    complaint = Complaint.new(subject: params[:subject],
                                description: params[:description],
                                image: params[:image],
                                latitude: params[:latitude],
                                longitude: params[:longitude],
                                district: params[:city],
                                state: params[:state],
                                pincode: params[:pincode],
                                user_id: get_logged_in_user_id,
                                status: "new" ,
                                priority: "new")

      if complaint.save
        # assign new complaint to respective district office
        create_new_complaint(complaint.id, complaint.state, complaint.district)

        render json: {status: "success", complaint: complaint}
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

private

  # assign new complaint to respective district office
  def create_new_complaint(complaint_id, state, district)

    district_office = DistrictOffice.where(state: state, district: district).first

    if district_office

  end

end
