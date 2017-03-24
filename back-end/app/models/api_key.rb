class ApiKey < ApplicationRecord

    has_secure_token :secret_key
    has_secure_token :user_token

end
