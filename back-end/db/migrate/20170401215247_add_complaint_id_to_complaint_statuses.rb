class AddComplaintIdToComplaintStatuses < ActiveRecord::Migration[5.0]
  def change
    add_column :complaint_statuses, :complaint_id, :integer
  end
end
