class UserController < ApplicationController

  def new
    @user = User.new
  end

  def create
    @user = User.new(params[:user])    # Not the final implementation!
    if @user.save
      # Handle a successful save.
    else
      render 'new'
    end
  end

  private
    def user_params
      params.require(:user).permit(:name, :contact, :email,
                                   :phone_no_verified, :aadhar_verified)
    end
end
