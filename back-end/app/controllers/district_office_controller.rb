class DistrictOfficeController < ApplicationController

   def find_rating
     solved_complaints = ComplaintStatus.where(district_office_id: district_office_id, status: "completed")
     pending_complaints = ComplaintStatus.where(district_office_id: district_office_id, status: "pending").count
     solved_complaints.each do |t|
        sla = Sla.where(category: t.department, subcategory: t.sub_category)
        time1 = sla.updated_at - sla.created_at
        time2 = t.updated_at - t.created_at
        time1 = 0
        delayed = 0
        if time1 >= time2
          timely = timely + 1
        else
          delayed = delayed + 1
        end
     end
   end
   render json: {timely: timely, delayed: delayed, pending: pending_complaints,
                solved_complaints: solved_complaints.count}

end
