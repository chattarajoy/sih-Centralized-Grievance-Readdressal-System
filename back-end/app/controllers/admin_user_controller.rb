class AdminUserController < ApplicationController

  before_action :check_user_logged_in_as_admin, only: [:update_password, :fetch_statistics, :fetch_complaints]

  def create
    admin_user = AdminUser.new(name: params[:name],
                                email: params[:email],
		                            phone: params[:phone],
                                designation: params[:designation],
		                            municipal_id: params[:municipal_id],
                                department: params[:department],
                                password: params[:password])

    if admin_user.save
      render json: {status: "success"}
    else
      render json: {status: "error", error_message: admin_user.errors.full_messages}
    end
  end

  def update_password

    if params[:email] && params[:password]

        user = AdminUser.where(email: params[:email]).first

          if user.id == get_logged_in_admin_id

            user.password = params[:password]

              if user.save
                render json: {status: "success"}
              else
                error_message = user.errors.full_messages
              end

          else
            error_message = "user not found"
            render json: {status: "error", error_message: error_message}
          end
    else
      render json: {status: "error", error_message: "params missing"}
    end
  end

  # Reset user password from link sent to email

  def reset_password

    if params[:access_token] && params[:secret_key] && params[:password]
      user_link = PasswordResetLink.where(access_token: params[:access_token],
                secret_key: params[:secret_key]).first
      if user_link
        user = AdminUser.find(user_link.user_id)
        user.password = params[:password]
        if user.save
          render json: {status: "success", message: "password changed"}
        else
          error_message = user.errors.full_messages
        end
      else
        error_message = "User not found"
      end
    else
      error_message = "Invalid Parameters"
    end
    render json: {status: "error", error_message: error_message}
  end

  # Request a password reset link on email\

  def request_password_reset
    if params[:email]
      user = AdminUser.where(email: params[:email]).first
      if user
        password_reset_link = PasswordResetLink.new(user_id: user.id)
        if password_reset_link.save
          #todo: send email
          render json: {status: "success", message: "Reset mail sent"} and return
        else
          error_message = password_reset_link.errors.full_messages
        end
      else
        error_message = "User not found"
      end
    else
      error_message = "Invalid parameters"
    end
    render json: {status: "error", error_message: error_message}
  end

  # fetch stats for admin's dashboard

  def fetch_statistics

    user = AdminUser.find(get_logged_in_user_id)

    if user.designation == "superviser"
      new_complaint = ComplaintStatus.where(admin_user_id: user.id, status: "new").count
      pending_complaint = ComplaintStatus.where(admin_user_id: user.id, status: "pending").count
      completed_complaint = ComplaintStatus.where(admin_user_id: user.id, status: "completed").count

    elsif user.designation == "ward officer"

      ward = WardOffice.find(user.municipal_id)

      new_complaint = ComplaintStatus.where(district_office_id: ward.district_office_id, ward_office_id: user.municipal_id,
                      category: user.designation, status: "new").count
      pending_complaint = ComplaintStatus.where(district_office_id: ward.district_office_id, ward_office_id: user.municipal_id,
                      category: user.designation, status: "pending").count
      completed_complaint = ComplaintStatus.where(district_office_id: ward.district_office_id, ward_office_id: user.municipal_id,
                      category: user.designation, status: "completed").count

      elsif user.designation == "district officer"

        new_complaint = ComplaintStatus.where(district_office_id: user.municipal_id, status: "new").count
        pending_complaint = ComplaintStatus.where(district_office_id: user.municipal_id, status: "pending").count
        completed_complaint = ComplaintStatus.where(district_office_id: user.municipal_id, status: "completed").count
    end

    render json: {stats: [new_complaint, pending_complaint, completed_complaint] }

  end

 # fetch complaints assigned to users
 def fetch_complaints

  user = AdminUser.find(get_logged_in_user_id)

  if user.designation == "superviser"
    new_complainst = ComplaintStatus.where(admin_user_id: user.id, status: "new")
    pending_complaints = ComplaintStatus.where(admin_user_id: user.id, status: "active")
    completed_complaints = ComplaintStatus.where(admin_user_id: user.id, status: "completed")

  elsif user.designation == "ward officer"

    ward = WardOffice.find(user.municipal_id)

    new_complaints = ComplaintStatus.where(admin_user_id: user.id, status: "new")
    pending_complaints = ComplaintStatus.where(district_office_id: ward.district_office_id, ward_office_id: user.municipal_id,
                    category: user.designation, status: "pending")
    completed_complaints = ComplaintStatus.where(district_office_id: ward.district_office_id, ward_office_id: user.municipal_id,
                    category: user.designation, status: "completed")

    elsif user.designation == "district officer"

      new_complaints = ComplaintStatus.where(admin_user_id: user.id, status: "new")
      pending_complaints = ComplaintStatus.where(district_office_id: user.municipal_id, status: "pending")
      completed_complaints = ComplaintStatus.where(district_office_id: user.municipal_id, status: "completed")
    end

  render json: {new_complaint: new_complaint, pending_complaint: pending_complaint, completed_complaint: completed_complaint}

 end
end
