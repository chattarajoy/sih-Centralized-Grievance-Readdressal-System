class DistrictOfficeController < ApplicationController

  def show_rating
    district_office_id = DistrictOffice.where(state: params[:state], district: params[:district]).first
    if district_office_id
      solved_complaints = ComplaintStatus.where(district_office_id: district_office_id, status: "completed")
      pending_complaints = ComplaintStatus.where(district_office_id: district_office_id, status: "pending").count
      total_complaints = ComplaintStatus.where(district_office_id: district_office_id)
      timely_count = 0
      delayed_count = 0
      solved_complaints.each do |complaint|
        sla = Sla.where(category: complaint.department, subcategory: complaint.sub_category)
        actual_time = (complaint.updated_at - complaint.created_at) / 3600
        expected_time = sla.time
        if expected_time >= actual_time
          timely_count = timely_count + 1
        else
          delayed_count = delayed_count + 1
        end
      end
      render json: {timely_solutions: timely_count, delayed_solutions: delayed_count, active_complaints: pending_complaints,
                    total_complaints: solved_complaints.count}
    else
      render json: {status: "error", error_message: "No data for given district"}
    end
  end
end
