class UserController < ApplicationController

  def signup
    user = User.new(name: params[:name], contact: params[:contact], email: params[:email],
     password: params[:password], phone_no_verified: false, aadhar_verified: false)    # Not the final implementation!

    if user.save
      render json: {status: "success", error_message: "done!"}
    else
      render json: {status: "error", error_message: user.errors.full_messages}
    end
  end

  def update_password

    user = User.find(email: params[:email])

    if user
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
  end

end
