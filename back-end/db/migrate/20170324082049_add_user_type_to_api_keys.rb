class AddUserTypeToApiKeys < ActiveRecord::Migration[5.0]
  def change
    add_column :api_keys, :user_type, :string
  end
end
