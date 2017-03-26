class AddEmailVerifiedToUser < ActiveRecord::Migration[5.0]
  def change
    add_column :users, :email_verified, :boolean
  end
end
