class UserController < ApplicationController

  def signup
    user = User.new(name: params[:name], contact: params[:contact], email: params[:email],
     password: params[:password], phone_no_verified: false, aadhar_verified: false)    # Not the final implementation!

    if user.save!
      render json: {status: 200, notice: "done!"}
    else
      render json: {status: 500, notice: "can't save"}
    end
  end

  def update_password

    user = User.find(email: params[:email])

    if user
      user.password = params[:password]
      if user.save
        render json: {status: "success"}
      else
        notice = "Update failed, contact admin"
      end
    else
      notice = "user not found"
      render json: {status: "error", notice: notice}
    end
  end

end
