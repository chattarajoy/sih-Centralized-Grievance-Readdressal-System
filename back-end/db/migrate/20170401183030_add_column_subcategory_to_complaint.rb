class AddColumnSubcategoryToComplaint < ActiveRecord::Migration[5.0]
  def change
    add_column :complaints, :subcategory, :string
  end
end
