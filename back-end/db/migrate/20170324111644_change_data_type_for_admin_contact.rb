class ChangeDataTypeForAdminContact < ActiveRecord::Migration[5.0]
  def change
    remove_column :admin_users, :phone
    add_column :admin_users, :phone, :string
  end
end
