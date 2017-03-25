class AdminUserController < ApplicationController

  def signup
    admin_user = AdminUser.new(name: params[:name], email: params[:email],
		 phone: params[:phone], access_level: params[:access_level],
		 municipality_id: params[:municipality_id], password: params[:password])   # Not the final implementation!

    if admin_user.save!
      render json: {status: "success", error_message: "done!"}
    else
      render json: {status: "error", error_message: admin_user.errors.full_messages}
    end
  end

  def update

    user = AdminUser.find(email: params[:email])



      if user
        if params[:password]
          user.password = params[:password]
        end
        if params[:phone]
          user.phone = params[:phone]
        end
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
