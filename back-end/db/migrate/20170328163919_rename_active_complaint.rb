class RenameActiveComplaint < ActiveRecord::Migration[5.0]
  def change
  	rename_column :active_complaints, :user_id , :ward_office_id 
  end
end
