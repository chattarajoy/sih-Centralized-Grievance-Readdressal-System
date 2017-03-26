class EmailController < ApplicationController

  before_action :check_user_logged_in, only: [:resend_verification]

  def verify
    if params[:verify_token] && params[:user_token]

      email_verification = Email.where(verify_token: params[:verify_token],
                                        user_token: params[:user_token]).first
      if email_verification
        user_id = email_verification.user_id
        user = User.find(user_id)
        #user.email_verified = true

        if user.save
          redirect_to('/') and return
        else
          error_message = "Database couldn't be updated"
        end

      else
        error_message = "Email not found"
      end

    else
      error_message = "Parameters missing"
    end

    render json: {status: "error", error_message: error_message}
  end

  def resend_verification

    user = User.find(get_logged_in_user_id)
    email_verification = Email.new(user_id: user.id)

    if email_verification.save
      SignupMailer.confirmation_email(user, email_verification).deliver
    else
      render json: {status: "success", message: "email not sent"}
    end

    render json: {status: "success", message: "email sent"}
  end

end
