class PasswordResetMailer < ApplicationMailer
	
	def reset_password_mailer(email, reset_password_link, website_link)
		@reset_password_link = reset_password_link
		@website_link = website_link
		mail(to: email, subject: "Reset password")
	end
end
