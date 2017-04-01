class AddDepartmentToComplaintStatuses < ActiveRecord::Migration[5.0]
  def change
    add_column :complaint_statuses, :department, :string
  end
end
