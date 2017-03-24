class AdminUserController < ApplicationController

  def signup
    admin_user = AdminUser.new(name: params[:name], email: params[:email],
		 phone: params[:phone], access_level: params[:access_level],
		 municipality_id: params[:municipality_id], password: params[:password])   # Not the final implementation!

    if admin_user.save!
      render json: {status: 200, notice: "done!"}
    else
      render json: {status: 500, notice: "can't save"}
    end
  end

end
