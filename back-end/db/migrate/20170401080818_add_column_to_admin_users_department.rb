class AddColumnToAdminUsersDepartment < ActiveRecord::Migration[5.0]
  def change
  	add_column :admin_users, :department, :string
  end
end
