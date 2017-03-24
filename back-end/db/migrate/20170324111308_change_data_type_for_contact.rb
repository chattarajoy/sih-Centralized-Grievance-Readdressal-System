class ChangeDataTypeForContact < ActiveRecord::Migration[5.0]
  def change
    remove_column :users, :contact
    add_column :users, :contact, :string
  end
end
