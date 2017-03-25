% updates paths for this project in matlab

path = fileparts(mfilename);
if ~isempty(path)
    cd(path);
end

% adding path with matlab sources
% addpath('.');

% adding path to corpus
addpath(getenv('SVHN_LOCAL'));