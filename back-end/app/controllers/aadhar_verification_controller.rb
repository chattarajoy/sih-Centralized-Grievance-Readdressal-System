class AadharVerificationController < ApplicationController

  before_action :check_user_logged_in

  def verify_aadhar_data
    if params[:aadhar_number] && params[:contact]
      aadhar = Aadhar.where(uid: params[:aadhar_number], phone: params[:contact]).first
      if aadhar
        otp = rand(10**6).to_s
        sms_otp = SmsOtp.new(user_id: get_logged_in_user_id, otp: otp)
        if sms_otp.save
          send_sms(aadhar.phone, otp + " is your otp for ASAR account verification")
          render json: {status: "success", message: "Otp sent"} and return
        else
          error_message = sms_otp.errors.full_messages
        end
      else
        error_message = "data not found"
      end
    else
      error_message = "Parameters missing"
    end
    render json: {status: "error", error_message: error_message}
  end

  def verify_otp
    if params[:otp]
      sms_otp = SmsOtp.where(user_id: get_logged_in_user_id, otp: params[:otp]).first
      if sms_otp
        user = User.find(get_logged_in_user_id)
        user.aadhar_verified = true
        user.phone_no_verified = true
        if user.save
          render json: {status: "success", message: "otp verified"} and return
        else
          error_message = user.errors.full_messages
        end
      else
        error_message = "Invalid OTP"
      end
    else
      error_message = "OTP missing"
    end
    render json: {status: "error", error_message: error_message}
  end
end
