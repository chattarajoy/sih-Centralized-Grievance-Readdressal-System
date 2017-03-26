class ChangeColumn < ActiveRecord::Migration[5.0]
  def change
    change_column :aadhars, :uid, :string, length: {minimum: 12, maximum: 12}, allow_blank: false
    change_column :aadhars, :phone, :string, length: {minimum: 10, maximum: 10}, allow_blank: false
  end
end
