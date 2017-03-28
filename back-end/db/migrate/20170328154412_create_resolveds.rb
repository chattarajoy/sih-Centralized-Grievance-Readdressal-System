class CreateResolveds < ActiveRecord::Migration[5.0]
  def change
    create_table :resolveds do |t|
      t.integer :complaint_id
      t.integer :districtoffice_id
      t.integer :wardoffice_id

      t.timestamps
    end
  end
end
