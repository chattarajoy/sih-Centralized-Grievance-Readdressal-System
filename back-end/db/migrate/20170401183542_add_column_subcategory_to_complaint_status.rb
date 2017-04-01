class AddColumnSubcategoryToComplaintStatus < ActiveRecord::Migration[5.0]
  def change
    rename_column :complaint_statuses, :category, :sub_category
  end
end
