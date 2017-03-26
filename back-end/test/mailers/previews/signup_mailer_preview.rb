# Preview all emails at http://localhost:3000/rails/mailers/signup_mailer
class SignupMailerPreview < ActionMailer::Preview
	def sample_mail_preview
    	SignupMailer.sample_email(User.first)
  	end

end
