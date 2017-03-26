class UserController < ApplicationController

  before_action :check_user_logged_in, only: [:update_password]

  def signup
    user = User.new(name: params[:name],
                    contact: params[:contact],
                    email: params[:email],
                    password: params[:password],
                    phone_no_verified: false,
                    aadhar_verified: false)

    if user.save
      email_verification = Email.new(user_id: user.id)
      if email_verification.save
        SignupMailer.confirmation_email(user, email_verification).deliver
      else
        render json: {status: "success", message: "email not sent"}
      end

      render json: {status: "success", message: "email sent"}
    else
      render json: {status: "error", error_message: user.errors.full_messages}
    end
  end

  def update_password

    if params[:email] && params[:password]

        user = User.where(email: params[:email]).first

          if user.id == get_logged_in_user_id

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

end
