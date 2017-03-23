class AdminUserController < ApplicationController
	def new
    @admin_user = AdminUser.new
  end

  def create
    @admin_user = AdminUser.new(params[:admin_user])    # Not the final implementation!
    if @admin_user.save
      # Handle a successful save.
    else
      render 'new'
    end
  end

  private
    def admin_user_params
      params.require(:admin_user).permit(:name, :email,
                                   :phone, :access_level, :municipality_id)
    end
end
