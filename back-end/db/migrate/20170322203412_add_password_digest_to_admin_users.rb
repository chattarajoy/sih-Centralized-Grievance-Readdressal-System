class AddPasswordDigestToAdminUsers < ActiveRecord::Migration[5.0]
  def change
    add_column :admin_users, :password_digest, :string
  end
end
