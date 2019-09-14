class UserController < ApplicationController

  before_action :check_user_logged_in, only: [:update_password]

  def signup
    user = User.new(name: params[:name],
                    contact: params[:contact],
                    email: params[:email],
                    password: params[:password],
                    phone_no_verified: false,
                    aadhar_verified: false,
                    email_verified: false)
    if user.save
      email_verification = Email.new(user_id: user.id)
      if email_verification.save
        #SignupMailer.confirmation_email(user, email_verification, @front_end_link).deliver
      else
        render json: {status: "success", message: "email not sent"}
      end
      render json: {status: "success", message: "email sent"}
    else
      render json: {status: "error", error_message: user.errors.full_messages}
    end
  end

  def update_password
    if params[:old_password] && params[:new_password]
      user = User.find(get_logged_in_user_id)
      if user && user.authenticate(params[:old_password])
        if user.authenticate(params[:new_password])
          render json: {status: "error", error_message: "old password and new password can't be the same!"} and return
        end
        user = User.find(get_logged_in_user_id)
        user.password = params[:new_password]
        if user.save
          render json: {status: "success"}
        else
          error_message = user.errors.full_messages
          render json: {status: "error", error_message: error_message}
        end
      else
        error_message = "Old password is incorrect"
        render json: {status: "error", error_message: error_message}
      end
    else
      render json: {status: "error", error_message: "params missing"}
    end
  end

  def reset_password
    if params[:access_token] && params[:secret_key] && params[:password]
      user_link = PasswordResetLink.where(access_token: params[:access_token], secret_key: params[:secret_key]).first
      if user_link
        user = User.find(user_link.user_id)
        user.password = params[:password]
        if user.save
          render json: {status: "success", message: "Password changed"}
        else
          error_message = user.errors.full_messages
        end
      else
        error_message = "User not found"
      end
    else
      error_message = "Invalid parameters"
    end
    render json: {status: "Error", error_message: error_message}
  end


  # Request a password reset link on email
  def request_password_reset
    if params[:email]
      user = User.where(email: params[:email]).first
      if user
        password_reset_link = PasswordResetLink.new(user_id: user.id)
        if password_reset_link.save
          #send email
          render json: {status: "success", message: "Reset mail sent"} and return
        else
          error_message = password_reset_link.errors.full_messages
        end
      else
        error_message = "User not found"
      end
    else
      error_message = "Error invalid parameters"
    end
    render json: {status: "Error", error_message: error_message}
  end
end
