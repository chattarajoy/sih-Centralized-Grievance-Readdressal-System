class ApplicationController < ActionController::API

	before_action :set_web_links

	def set_web_links
		@back_end_link = "54.169.134.133"
		@front_end_link = ""
	end


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

	def check_user_logged_in_as_admin

		if check_user_logged_in
		end
	end

	def get_logged_in_user_id

		user = ApiKey.where(user_token: request.headers["HTTP_ACCESS_TOKEN"],
		secret_key: request.headers["HTTP_SECRET_KEY"]).first
		return user.user_id

	end

	def send_sms(to, message_body)

		require 'twilio-ruby'

		# put your own credentials here
		account_sid = ENV['TWILIO_ACCOUNT_SID']
		auth_token = ENV['TWILIO_AUTH_TOKEN']

		# set up a client to talk to the Twilio REST API
		@client = Twilio::REST::Client.new account_sid, auth_token

		@client.account.messages.create({
			:from => '+14843417052',
			:to => "+91" + to,
			:body => message_body,
		})

	end

end
