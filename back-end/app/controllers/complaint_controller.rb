class ComplaintController < ApplicationController

  before_action :check_user_logged_in

  def index
    render json: {status: "success", user_id: get_logged_in_user_id}
  end

  def create
    complaint = Complaint.new(subject: params[:subject],
                                description: params[:description],
                                image: params[:image],
                                latitude: params[:latitude],
                                longitude: params[:longitude],
                                city: params[:city],
                                state: params[:state],
                                pincode: params[:pincode],
                                user_id: get_logged_in_user_id,
                                status: "new" ,
                                priority: "new")

      if complaint.save
        render json: {status: "success"}
      else
        render json: {status: "error", error_message: complaint.errors.full_messages}
      end
  end

end
