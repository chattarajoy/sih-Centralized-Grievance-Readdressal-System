class AdminUserController < ApplicationController

  before_action :check_user_logged_in, only: [:update_password]

  def signup
    admin_user = AdminUser.new(name: params[:name],
                                email: params[:email],
		                            phone: params[:phone],
                                designation: params[:designation],
		                            municipal_id: params[:municipal_id],
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

end
