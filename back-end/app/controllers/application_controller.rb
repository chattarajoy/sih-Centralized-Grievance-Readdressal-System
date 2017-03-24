class ApplicationController < ActionController::API

    def check_user_logged_in

        if params["access_token"]

          user = ApiKey.where(secret_key: params["access_token"])

          if user
            return true
          else
            return false
          end

        else
          render json: {status: 401, notice: "Access denied"}
          return false
        end
    end

end
