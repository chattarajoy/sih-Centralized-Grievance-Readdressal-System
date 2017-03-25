class AuthController < ApplicationController

  before_action :check_user_logged_in, only: [:logout_user, :logout_admin]

  def user_login

    if params[:password] && params[:email]

        user = User.where(email: params[:email]).first

        if user && user.authenticate(params[:password])
            api_key = ApiKey.new
            api_key.user_id = user.id
            api_key.user_type = "visitor"
            api_key.save
            secret_key = api_key.secret_key
            access_token = api_key.user_token

            render json: {status: "success", access_token: access_token, secret_key: secret_key}
        else
          error_message = "Invalid email or password"
        end
    else
        error_message = "Invalid Params"
    end

    render json: {status: "error", error_message: error_message} unless access_token

  end

  def admin_login

      if params[:password] && params[:email]

          user = AdminUser.where(email: params[:email]).first

          if user && user.authenticate(params[:password])
              api_key = ApiKey.new
              api_key.user_id = user.id
              api_key.user_type = "admin"
              api_key.save
              secret_key = api_key.secret_key
              access_token = api_key.user_token

              render json: {status: "success", access_token: access_token, secret_key: secret_key}
          else
            error_message = "Invalid email or password"
          end
      else
          error_message = "Invalid Params"
      end
      render json: {status: "error", error_message: error_message} unless access_token

  end

  def logout_user

    key = ApiKey.where(secret_key: request.headers["HTTP_SECRET_KEY"],
      user_token: request.headers["HTTP_ACCESS_TOKEN"], user_type: "visitor").first

    if key.delete
      render json: {status: "success"}
    else
      render json: {status: "error", error_message: key.errors.full_messages}
    end
  end

  def logout_admin

    key = ApiKey.where(secret_key: request.headers["HTTP_SECRET_KEY"],
      user_token: request.headers["HTTP_ACCESS_TOKEN"], user_type: "admin").first

    if key.delete
      render json: {status: "success"}
    else
      render json: {status: "error", error_message: key.errors.full_messages}
    end
  end

end
