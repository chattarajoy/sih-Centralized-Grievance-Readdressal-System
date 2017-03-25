class AdminUserController < ApplicationController

  before_action :check_user_logged_in, only: [:update_password]

  def signup
    admin_user = AdminUser.new(name: params[:name],
                                email: params[:email],
		                            phone: params[:phone],
                                access_level: params[:access_level],
		                            municipality_id: params[:municipality_id],
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

end
