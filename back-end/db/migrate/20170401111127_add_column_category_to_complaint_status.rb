class AddColumnCategoryToComplaintStatus < ActiveRecord::Migration[5.0]
  def change
    add_column :complaint_statuses, :category, :string
  end
end
