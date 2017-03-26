class ApplicationController < ActionController::API

private

    def check_user_logged_in

        if request.headers["HTTP_ACCESS_TOKEN"] && request.headers["HTTP_SECRET_KEY"]

          user = ApiKey.where(secret_key: request.headers["HTTP_SECRET_KEY"],
            user_token: request.headers["HTTP_ACCESS_TOKEN"]).first

            if user
              return true
            else
              render json: {status: "error", error_message: "Access denied"}
              return false
            end

        else
          render json: {status: "error", error_message: "Access denied"}
          return false
        end
    end

    def get_logged_in_user_id

      user = ApiKey.where(user_token: request.headers["HTTP_ACCESS_TOKEN"],
       secret_key: request.headers["HTTP_SECRET_KEY"]).first
      return user.user_id

    end

end
