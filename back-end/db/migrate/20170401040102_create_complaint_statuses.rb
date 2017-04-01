class CreateComplaintStatuses < ActiveRecord::Migration[5.0]
  def change
    create_table :complaint_statuses do |t|
      t.string :admin_user_id
      t.string :district_office_id
      t.string :ward_office_id
      t.string :status
      t.string :priority

      t.timestamps
    end
  end
end
