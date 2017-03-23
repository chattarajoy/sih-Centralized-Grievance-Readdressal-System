class ComplaintController < ApplicationController
  def new
    @complaint = Complaint.new
  end

  def create
    @complaint = Complaint.new(complaint_params)
  end

  def update
  end
end
