class AddUserTokenToApiKeys < ActiveRecord::Migration[5.0]
  def change
    add_column :api_keys, :user_token, :string
  end
end
