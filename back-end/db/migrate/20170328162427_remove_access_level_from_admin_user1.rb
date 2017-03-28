class RemoveAccessLevelFromAdminUser1 < ActiveRecord::Migration[5.0]
  def change
    remove_column :admin_users, :access_level
  end
end
