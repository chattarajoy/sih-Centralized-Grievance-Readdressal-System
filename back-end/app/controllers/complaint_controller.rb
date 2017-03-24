class ComplaintController < ApplicationController


  before_action :check_user_logged_in

  def new
    @complaint = Complaint.new
  end

  def create
    @complaint = Complaint.new(complaint_params)
  end

  def update
  end

end
