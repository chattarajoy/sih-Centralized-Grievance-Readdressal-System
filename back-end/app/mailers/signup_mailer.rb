class SignupMailer < ApplicationMailer

	def confirmation_email(user, email_verification)
		@user = user
		@verification_code = email_verification
		mail(to: @user.email, subject: 'Thank you for Registering on ASAR')
	end
end
