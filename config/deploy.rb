# config valid only for Capistrano 3.1
lock '3.2.1'

set :application, 'sample'

# Default branch is :master
# ask :branch, proc { `git rev-parse --abbrev-ref HEAD`.chomp }.call

# Default deploy_to directory is /var/www/my_app
set :deploy_to, '/tmp/sample/application'

# Default value for :scm is :git
set :scm, :local_file
set :repo_url, 'build/distributions/sample-1.0.0.tar'
set :repo_name, 'sample-1.0.0.tar'

#set :ssh_options, {
#  user: 'ubuntu',
#  keys: %w(/Users/qma/Documents/workspace/java/me/sample/config/admin-key.pem),
#  forward_agent: false,
#  auth_methods: %w(publickey)
#}

set :use_sudo, true

# Default value for :format is :pretty
# set :format, :pretty

# Default value for :log_level is :debug
# set :log_level, :debug

# Default value for :pty is false
# set :pty, true

# Default value for :linked_files is []
# set :linked_files, %w{config/database.yml}

# Default value for linked_dirs is []
set :linked_dirs, %w(logs)

# Default value for default_env is {}
# set :default_env, { path: "/opt/ruby/bin:$PATH" }

# Default value for keep_releases is 5
# set :keep_releases, 5

namespace :deploy do

  desc 'Start application'
  task :start do
    on roles(:app), in: :sequence, wait: 5 do
      #execute "/etc/init.d/sensu-server start"
    end
  end

  desc 'Stop application'
  task :stop do
    on roles(:app), in: :sequence, wait: 5 do
      #execute "/etc/init.d/sensu-server stop"
    end
  end

  desc 'Restart application'
  task :restart do
    on roles(:app), in: :sequence, wait: 5 do
      #execute "/etc/init.d/sensu-server restart"
    end
  end

  after :finishing, 'deploy:restart'
  after :finishing, 'deploy:cleanup'
end

namespace :apps do
  task :deploy do
    on roles(:app) do
      execute "ls"
    end
  end

  after 'deploy:finished', 'apps:deploy'


end
