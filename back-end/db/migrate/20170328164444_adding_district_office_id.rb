class AddingDistrictOfficeId < ActiveRecord::Migration[5.0]
  def change
  	add_column :active_complaints , :district_office_id ,:integer
  end
end
