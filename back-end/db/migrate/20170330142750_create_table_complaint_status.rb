class CreateTableComplaintStatus < ActiveRecord::Migration[5.0]
  def change
    create_table :table_complaint_statuses do |t|
      t.integer :district_office_id
      t.integer :ward_office_id
      t.integer :complaint_id
    end
  end
end
