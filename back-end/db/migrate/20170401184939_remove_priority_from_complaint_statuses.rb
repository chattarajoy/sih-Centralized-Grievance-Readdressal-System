class RemovePriorityFromComplaintStatuses < ActiveRecord::Migration[5.0]
  def change
    remove_column :complaint_statuses, :priority
  end
end
