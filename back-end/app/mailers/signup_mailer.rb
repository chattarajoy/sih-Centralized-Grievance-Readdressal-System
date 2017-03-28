class SignupMailer < ApplicationMailer

	def confirmation_email(user, email_verification, website_link)
		@user = user
		@verification_code = email_verification
		@website_link = website_link
		mail(to: @user.email, subject: 'Thank you for Registering on ASAR')
	end
end
