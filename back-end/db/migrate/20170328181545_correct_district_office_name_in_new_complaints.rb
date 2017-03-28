class CorrectDistrictOfficeNameInNewComplaints < ActiveRecord::Migration[5.0]
  def change
    rename_column :new_complaints, :districtoffice_id, :district_office_id
  end
end
