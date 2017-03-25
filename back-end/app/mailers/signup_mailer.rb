class SignupMailer < ApplicationMailer

	def sample_email(user)
		@user=user
		mail(to: "@user.email", subject: 'Confirmation mail for completion of registratio of ASAR')
	end
end
