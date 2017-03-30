class AddStatusToComplaintStatuses < ActiveRecord::Migration[5.0]
  def change
    rename_table :table_complaint_statuses, :complaint_statuses
    add_column :complaint_statuses, :status, :string
  end
end
