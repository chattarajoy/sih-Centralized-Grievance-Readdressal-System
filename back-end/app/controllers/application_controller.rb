class ApplicationController < ActionController::API

    def check_user_logged_in

        if request.headers["HTTP_ACCESS_TOKEN"] && request.headers["HTTP_SECRET_KEY"]

          user = ApiKey.where(secret_key: request.headers["HTTP_SECRET_KEY"],
            user_token: request.headers["HTTP_ACCESS_TOKEN"], user_type: "visitor").first

            if user
              return true
            else
              render json: {status: "error", notice: "Access denied"}
              return false
            end

        else
          render json: {status: "error", notice: "Access denied"}
          return false
        end
    end

end
